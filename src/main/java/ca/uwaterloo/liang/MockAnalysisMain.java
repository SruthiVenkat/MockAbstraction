package ca.uwaterloo.liang;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ca.uwaterloo.liang.util.Utility;
import soot.G;
import soot.PackManager;
import soot.PointsToAnalysis;
import soot.Scene;
import soot.SceneTransformer;
import soot.SootClass;
import soot.SootMethod;
import soot.Transform;
import soot.Transformer;
import soot.Unit;
import soot.jimple.InvokeExpr;
import soot.jimple.JimpleBody;
import soot.jimple.toolkits.callgraph.CallGraph;
import soot.options.Options;
import soot.tagkit.AnnotationTag;
import soot.toolkits.graph.ExceptionalUnitGraph;

public class MockAnalysisMain extends SceneTransformer {
    private static String benchmark;
    private static String output_path;
    
    private static final Logger logger = LoggerFactory.getLogger(PackManager.class);
    public static void main(String[] args) throws IOException {
        PackManager.v().getPack("wjtp").add(new Transform("wjtp.initialTransform", new MockAnnotationTransformer()) {
        });
        PackManager.v().getPack("wjtp").add(new Transform("wjtp.myTransform", new MockAnalysisMain()) {
        });
        Options.v().set_prepend_classpath(true);
        Options.v().set_verbose(true);
        Options.v().set_whole_program(true);
        Options.v().set_output_format(1); // Output format in .jimple file
        Options.v().set_allow_phantom_refs(true);
        Options.v().set_xml_attributes(true);
        List<String> pd = new ArrayList<>();
        pd.add("-main-class");
        pd.add(args[0]);
        pd.add("-process-dir");
        pd.add(args[1]);
        pd.add("-process-dir");
        pd.add(args[2]);
        pd.add("-p");
        pd.add("jj.tr");
        pd.add("enabled:true");
        Options.v().set_soot_classpath(args[3]);
        MockAnalysisMain.benchmark = args[4];
        MockAnalysisMain.output_path = args[5];
        System.out.println("args[0]: " + args[0]);
        System.out.println("args[1]: " + args[1]);
        System.out.println("args[2]: " + args[2]);
        System.out.println("args[3]: " + args[3]);
        System.out.println("args[4]: " + args[4]);
        System.out.println("args[5]: " + args[5]);
        soot.Main.main(pd.toArray(new String[0]));
    }
        
    private CallGraph myCallGraph;
    
    private PointsToAnalysis myPointsToAnalysis;
    
    /**
     * All the classes of the application to analyze
     */ 
    private HashMap<String, SootClass> myAppClasses;
    private Collection<SootClass> colAppClasses;
    
    /**
     * All the methods of the applications classes to analyze
     */
    private ArrayList<SootMethod> myAppMethods;
    
    /**
     * Each class mapped to its corresponding methods
     */
    private HashMap<String, ArrayList<SootMethod>> myClassMethods;
    
    
    /**
     * Each method mapped to its summary
     */
    private HashMap<SootMethod, ProcSummary> myProcSummaries;
        
    /**
     * Each method mapped to its callees
     */
   // private HashMap<String, ArrayList<SootMethod> > myCallees;
        
    private MockAnalysis myMAnalysis;
        
    public MockAnalysisMain() {
        super();
        
        myProcSummaries = new HashMap<SootMethod, ProcSummary>();
            
        myClassMethods = new HashMap<String, ArrayList<SootMethod> >();
            
       // myCallees = new HashMap<String, ArrayList<SootMethod> >();
                
        myAppMethods = new ArrayList<SootMethod>();
        
        myAppClasses = new HashMap<String, SootClass>();
    }


    @Override
    protected void internalTransform(String phaseName, Map<String, String> options) {
        // TODO Auto-generated method stub
        Iterator<SootClass> itAppClasses = Scene.v().getApplicationClasses().iterator();
        while(itAppClasses.hasNext()) {
            SootClass nextClass = itAppClasses.next();
            myAppClasses.put(nextClass.getName(), nextClass);
        }
        colAppClasses = myAppClasses.values();
    
        myCallGraph = Scene.v().getCallGraph();
     
        // get points to analysis object
        myPointsToAnalysis = Scene.v().getPointsToAnalysis();
        
        //Compute summaries of all methods present in the call graph
        
        for (SootClass sc : colAppClasses) {
            myAppMethods.addAll(sc.getMethods());           
            myClassMethods.put(sc.getName(), new ArrayList<SootMethod>(sc.getMethods()) );
        } 
        
        ProcSummary mockSummary = null;  
           
        ExceptionalUnitGraph aCfg = null;
            
        boolean mySAInst = false;
            
        G.v().out.println("Number of methods to be analyzed: " + myAppMethods.size() );
        
        for (SootMethod method : myAppMethods) {   
            if (method.hasActiveBody() && 
                    (isTestCase(method) || isBeforeMethod(method) || isAfterMethod(method)) ) {
                JimpleBody body = (JimpleBody) method.getActiveBody();
                
                /*if (method.getDeclaringClass().getName().contains("PayRollAnnotationMockTest")) {
                    G.v().out.println(body);
                }*/
                mockSummary = new ProcSummary(method);
                
                aCfg = new ExceptionalUnitGraph(method.getActiveBody());
                
                if (mySAInst){
                     myMAnalysis.analyze(aCfg, method);
                     myMAnalysis.updateInvocations(aCfg, method);
                } else {
                     myMAnalysis = new MockAnalysis(aCfg);
                     myMAnalysis.updateInvocations(aCfg);
                     mySAInst = true;
                }
                
                mockSummary.setMustMocks( myMAnalysis.getMustMocks() );           
                
                mockSummary.setTotalInvokeExprs( myMAnalysis.getTotalInvokeExprs() );
                
                mockSummary.setInvokeExprsOnMocks( myMAnalysis.getInvokeExprsOnMocks() );
                    
               // myCallees.put(method.getSignature(), myMAnalysis.getInvokedMethods());
                
                myProcSummaries.put(method, mockSummary);
            }
        }
            
        printOutput();
        
        int[] benchmark_mock_stats = calculateMockStats();
        StringBuffer msg = new StringBuffer();
        msg.append(" ====================================== \n")
        .append("Benchmark ").append(MockAnalysisMain.benchmark).append(" Mock Stats")
        .append("\n");
        msg.append("Total Number of Test Methods with MustMock: ").append(benchmark_mock_stats[0])
        .append("\n");
        msg.append("Total Number of Test Methods with ArrayMock in class: ").append(benchmark_mock_stats[1])
        .append("\n");
        msg.append("Total Number of Test Methods with Collection in class: ").append(benchmark_mock_stats[2])
        .append("\n");
        G.v().out.println(msg);
        
        printMockInvocationStats();
    }
    
    private void printMockInvocationStats() {
        StringBuffer msg = new StringBuffer();
        msg.append(" ====================================== \n")
        .append(" INVOCATION STATISTICS \n\n");
        
        int total_count = 0, mocks_count = 0;
        for(SootClass nc : colAppClasses) {
            msg.append("** CLASS ").append(nc.toString())
            .append("\n");
            
            List<SootMethod> ncM = nc.getMethods();
            
            ProcSummary pSmy = null;
            
            for(SootMethod m : ncM) {
                pSmy = myProcSummaries.get(m);
                if (pSmy == null) 
                    continue;
                ArrayList<InvokeExpr> totalInvokes = pSmy.getTotalInvokeExprs();
                if (!totalInvokes.isEmpty())
                    total_count += totalInvokes.size();
                
                ArrayList<InvokeExpr> invokeOnMocks = pSmy.getInvokeExprsOnMocks();
                
                if (!invokeOnMocks.isEmpty()) {
                    mocks_count += invokeOnMocks.size();
                    msg.append("\tmethod ").append(m.getName()).append(" : \n");
                    msg.append("\tTotal invocations on Mocks ").append(invokeOnMocks.size()).append(" : \n\n");
                    for (InvokeExpr invkExpr : invokeOnMocks)
                        msg.append("\tInvokeExpr: ").append(invkExpr).append("\n\n");
                }
            }
        }
        msg.append(" ====================================== \n");
        msg.append("Benchmark Overall Stats \n");
        msg.append("Total Invocations: ").append(total_count).append("\n");
        msg.append("Invocations On Mocks: ").append(mocks_count).append("\n");
        
        G.v().out.println(msg);
    }
    
    private int[] calculateMockStats() {
        int[] class_mocks = new int[3];
        int[] benchmark_mock_stats = new int[3];
                    
        for(SootClass nc : colAppClasses) {     
            List<int[]> mockStats = Utility.gatherMocksStats(nc, myProcSummaries);
            StringBuffer msg = new StringBuffer();
            msg.append(" ====================================== \n")
            .append("** CLASS ").append(nc.toString())
            .append("\n");  
            class_mocks = new int[3];
            if (mockStats.isEmpty() || mockStats.size() == 0)
                continue;
            for (int[] mock : mockStats) {
                // # of methods in the class with MustMock
                class_mocks[0] += mock[0];
                // # of methods in the class with ArrayMock
                class_mocks[1] += mock[1];
                // # of methods in the class with CollectionMock
                class_mocks[2] += mock[2];
            }
            msg.append("Number of Methods with MustMock in class: ").append(class_mocks[0])
            .append("\n");
            msg.append("Number of Methods with ArrayMock in class: ").append(class_mocks[1])
            .append("\n");
            msg.append("Number of Methods with Collection in class: ").append(class_mocks[2])
            .append("\n");
            G.v().out.println(msg);
            benchmark_mock_stats[0] += class_mocks[0];
            benchmark_mock_stats[1] += class_mocks[1];
            benchmark_mock_stats[2] += class_mocks[2];
        }
        
        return benchmark_mock_stats;
    }
    
    private void printOutput() {
        StringBuffer msg = new StringBuffer();
                    
        for(SootClass nc : colAppClasses) {     
            msg.append( Utility.printMustMocks(nc, myProcSummaries) );
        }   
            
        G.v().out.println(msg);
    }
    
    private static boolean isBeforeMethod(SootMethod sm) {
        // JUnit 3
        if ((sm.getName().equals("init()") ||  sm.getName().equals("setUp()")) 
                && sm.getParameterCount() == 0 && sm.getReturnType().toString() == "void") {
            //System.out.println("Test case found: " + sm.getSubSignature());
            return true;
        }

        // JUnit 4+
        List<soot.tagkit.Tag> smTags = sm.getTags();
        soot.tagkit.VisibilityAnnotationTag tag = (soot.tagkit.VisibilityAnnotationTag) sm
                .getTag("VisibilityAnnotationTag");
        if (tag != null) {
            for (AnnotationTag annotation : tag.getAnnotations()) {
                if (annotation.getType().equals("Lorg/junit/Before;") || annotation.getType().equals("Lorg/junit/BeforeClass;")
                        || annotation.getType().equals("Lorg/junit/jupiter/api/BeforeEach;") 
                        || annotation.getType().equals("Lorg/junit/jupiter/api/BeforeAll;")) {
                    //System.out.println("Test case found: " + sm.getSignature());
                    return true;
                }
            }
        }
        return false;
    }
    
    private static boolean isAfterMethod(SootMethod sm) {
        // JUnit 3
        if (sm.getName().equals("tearDown()") 
                && sm.getParameterCount() == 0 && sm.getReturnType().toString() == "void") {
            //System.out.println("Test case found: " + sm.getSubSignature());
            return true;
        }

        // JUnit 4+
        List<soot.tagkit.Tag> smTags = sm.getTags();
        soot.tagkit.VisibilityAnnotationTag tag = (soot.tagkit.VisibilityAnnotationTag) sm
                .getTag("VisibilityAnnotationTag");
        if (tag != null) {
            for (AnnotationTag annotation : tag.getAnnotations()) {
                if (annotation.getType().equals("Lorg/junit/After;") || annotation.getType().equals("Lorg/junit/AfterClass;")
                        || annotation.getType().equals("Lorg/junit/jupiter/api/AfterEach;") 
                        || annotation.getType().equals("Lorg/junit/jupiter/api/AfterAll;")) {
                    //System.out.println("Test case found: " + sm.getSignature());
                    return true;
                }
            }
        }
        return false;
    }
    
    private static boolean isTestCase(SootMethod sm) {
        // JUnit 3
        if (sm.getName().toLowerCase().startsWith("test") && sm.getParameterCount() == 0 && sm.getReturnType().toString() == "void") {
            //System.out.println("Test case found: " + sm.getSubSignature());
            return true;
        }

        // JUnit 4+

        List<soot.tagkit.Tag> smTags = sm.getTags();
        soot.tagkit.VisibilityAnnotationTag tag = (soot.tagkit.VisibilityAnnotationTag) sm
                .getTag("VisibilityAnnotationTag");
        if (tag != null) {
            for (AnnotationTag annotation : tag.getAnnotations()) {
                if (annotation.getType().equals("Lorg/junit/Test;") 
                        || annotation.getType().equals("Lorg/junit/jupiter/api/Test;")) {
                    //System.out.println("Test case found: " + sm.getSignature());
                    return true;
                }
            }
        }
        return false;
    }
}
