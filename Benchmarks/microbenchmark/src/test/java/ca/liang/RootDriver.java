package ca.liang;

public class RootDriver {
	public static void main(String[] argv) {
		ca.liang.PayRollArrayNoMockTest class1 = new ca.liang.PayRollArrayNoMockTest();
		class1.init();
		class1.testNoEmployees();
		class1.testSingleEmployeeLocalArrayNomock();
		ca.liang.PayRollArrayMockTest class2 = new ca.liang.PayRollArrayMockTest();
		class2.init();
		class2.testSingleEmployee();
		class2.testEmployeesPaidIntra();
		class2.testSingleEmployeeFieldArrayMock();
		class2.testSingleEmployeeLocalArrayMock();
		class2.testAllEmployeesArePaidArrayIntra();
		class2.testAllEmployeesArePaidArrayInter();
		ca.liang.PayRollMultipleEmployee2Test class3 = new ca.liang.PayRollMultipleEmployee2Test();
		class3.init();
		class3.testAllEmployeesArePaid();
		ca.liang.PayRollMultipleEmployeeTest class4 = new ca.liang.PayRollMultipleEmployeeTest();
		class4.init();
		class4.testAllEmployeesArePaid1();
		class4.testAllEmployeesArePaid2();
		ca.liang.PayRollMockTest class5 = new ca.liang.PayRollMockTest();
		class5.init();
		class5.testMerge();
		class5.testMerge2();
		class5.testMerge3();
		class5.testNoEmployees();
		class5.testNoEmployeesIntra();
		class5.testMockCallInsideLambda();
		class5.testAddAll();
		class5.testIteration();
		class5.testVector();
		class5.testVector2();
		class5.testVectorToArray();
		class5.testArrayToVector();
		class5.testSingleEmployee();
		class5.testTransitiveCall();
		class5.testSingleEmployeeMockInter();
		class5.testSingleEmployeeMockCallout();
		class5.testEmployeeIsPaid();
		class5.testEmployeeIsPaid_singleVerify();
		class5.testInteractionOrder();
		ca.liang.PayRollMockMarginalCollectionsTest class6 = new ca.liang.PayRollMockMarginalCollectionsTest();
		class6.init();
		class6.test0();
		class6.test1();
		class6.test2();
		class6.test3();
		class6.test4();
		class6.test5();
		class6.test6();
		class6.testPQ1();
		class6.testPQ2();
		class6.testPQ3();
		class6.testPQ4();
		class6.testTS();
		class6.testTS1();
		class6.testDQ();
		class6.testDescendingSet();
		class6.testFloorCeiling();
		class6.testHigherLower();
		class6.testHeadSet();
		class6.testTailSet();
		class6.testSubSet();
		ca.liang.DirtyTest class7 = new ca.liang.DirtyTest();
		class7.init();
		class7.testSingleEmployeeFieldArrayNomock();
		ca.liang.PayRollAnnotationMockTest class8 = new ca.liang.PayRollAnnotationMockTest();
		class8.init();
		class8.testEmployeesPaidIntra();
		class8.testSingleEmployee();
		class8.testEmployeeIsPaid();
		class8.testBankService();
	}
}