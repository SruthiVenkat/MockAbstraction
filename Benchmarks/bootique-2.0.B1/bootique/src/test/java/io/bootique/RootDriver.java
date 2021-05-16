package io.bootique;

public class RootDriver {
	public static void main(String[] argv) {
		io.bootique.value.Driver class1 = new io.bootique.value.Driver();
		class1.runall();
		io.bootique.config.jackson.merger.Driver class2 = new io.bootique.config.jackson.merger.Driver();
		class2.runall();
		io.bootique.config.jackson.Driver class3 = new io.bootique.config.jackson.Driver();
		class3.runall();
		io.bootique.meta.module.Driver class4 = new io.bootique.meta.module.Driver();
		class4.runall();
		io.bootique.command.Driver class5 = new io.bootique.command.Driver();
		class5.runall();
		io.bootique.log.Driver class6 = new io.bootique.log.Driver();
		class6.runall();
		io.bootique.names.Driver class7 = new io.bootique.names.Driver();
		class7.runall();
		io.bootique.shutdown.Driver class8 = new io.bootique.shutdown.Driver();
		class8.runall();
		io.bootique.config.jackson.path.Driver class9 = new io.bootique.config.jackson.path.Driver();
		class9.runall();
		io.bootique.it.Driver class10 = new io.bootique.it.Driver();
		class10.runall();
		io.bootique.config.jackson.parser.Driver class11 = new io.bootique.config.jackson.parser.Driver();
		class11.runall();
		io.bootique.run.Driver class12 = new io.bootique.run.Driver();
		class12.runall();
		io.bootique.help.Driver class13 = new io.bootique.help.Driver();
		class13.runall();
		io.bootique.meta.application.Driver class14 = new io.bootique.meta.application.Driver();
		class14.runall();
		io.bootique.jopt.Driver class15 = new io.bootique.jopt.Driver();
		class15.runall();
		io.bootique.terminal.Driver class16 = new io.bootique.terminal.Driver();
		class16.runall();
		io.bootique.jackson.Driver class17 = new io.bootique.jackson.Driver();
		class17.runall();
		io.bootique.resource.Driver class18 = new io.bootique.resource.Driver();
		class18.runall();
		io.bootique.unit.Driver class19 = new io.bootique.unit.Driver();
		class19.runall();
		io.bootique.help.config.Driver class20 = new io.bootique.help.config.Driver();
		class20.runall();
		io.bootique.meta.config.Driver class21 = new io.bootique.meta.config.Driver();
		class21.runall();
		io.bootique.BootiqueTest class22 = new io.bootique.BootiqueTest();
		class22.before();
		class22.testCreateInjector_Modules_Instances();
		class22.testCreateInjector_Modules_Types();
		io.bootique.Bootique_CliOptionsIT class23 = new io.bootique.Bootique_CliOptionsIT();
		class23.testConfigOption();
		class23.testConfigOptions();
		class23.testHelpOption();
		class23.testHelpOption_Short();
		class23.testNoHelpOption();
		class23.testOverlappingOptions();
		class23.testNameConflict_TwoOptions();
		class23.testNameConflict_TwoCommands();
		class23.testOverlappingOptions_Short();
		class23.testCommandWithOptionNameOverlap();
		class23.testCommand_IllegalShort();
		class23.testCommand_ExplicitShort();
		class23.testOverlappingCommands_IllegalShort();
		class23.testIllegalAbbreviation();
		class23.testOverlappingCommands_Short();
		class23.testDefaultCommandOptions();
		class23.testOption_OverrideConfig();
		class23.testOptionPathAbsentInYAML();
		class23.testOptionsCommandAndModuleOverlapping();
		class23.testOptionsOrder_OnCLI();
		class23.testOptionsWithOverlappingPath_OverrideConfig();
		class23.testOptionWithNotMappedConfigPath();
		class23.testOptionConfigFile_OverrideConfig();
		class23.testMultipleOptionsConfigFiles_OverrideInCLIOrder();
		class23.testOptionDefaultValue();
		class23.testMissingOptionDefaultValue();
		class23.testCommandWithOptionWithDefaultValue();
		io.bootique.BootiqueExceptionsHandlerIT class24 = new io.bootique.BootiqueExceptionsHandlerIT();
		class24.testCli_BadOption();
		class24.testCli_TwoCommands();
		class24.testConfig_FileNotFound();
		class24.testConfig_BadUrl();
		class24.testConfig_BadUrlProtocol();
		class24.testDI_ProviderMethodBqException();
		class24.testDI_ProviderMethodNPException();
		class24.testModules_CircularOverrides();
		class24.testModules_MultipleOverrides();
		class24.testDI_TwoCommandsSameName();
		io.bootique.ConfigModuleTest class25 = new io.bootique.ConfigModuleTest();
		class25.testDefaultConfigPrefix();
		class25.testConfigPrefix();
		class25.testConfigConfig();
		io.bootique.BootiqueUtilsTest class26 = new io.bootique.BootiqueUtilsTest();
		class26.testToArray();
		class26.testMergeArrays();
		class26.moduleProviderDependencies();
		class26.moduleProviderDependenciesTwoLevels();
		class26.moduleProviderDependenciesCircular();
		io.bootique.Bootique_Configuration_PropertiesIT class27 = new io.bootique.Bootique_Configuration_PropertiesIT();
		class27.testOverride();
		class27.testOverrideNested();
		class27.testOverrideValueArray();
		class27.testOverrideValueArray_Empty();
		class27.testOverrideObjectArray();
		class27.testOverrideObjectArray_AddValue();
		io.bootique.Bootique_ConfigurationIT class28 = new io.bootique.Bootique_ConfigurationIT();
		class28.testEmptyConfig();
		class28.testConfigBoundToString();
		class28.testCombineConfigAndEmptyConfig();
		class28.testCombineConfigs();
		class28.testCombineConfigs_ReverseOrder();
		class28.testDIConfig();
		class28.testDIConfig_VsCliOrder();
		class28.testDIOnOptionConfig();
		class28.testDIOnOptionConfig_OverrideWithOption();
		class28.testConfigEnvOverrides_Alias();
		io.bootique.BootiqueIT class29 = new io.bootique.BootiqueIT();
		class29.testExec();
		class29.testExec_Failure();
		class29.testExec_Exception();
		class29.testAutoLoadedProviders();
		class29.testCreateInjector();
		class29.testApp_Collection();
		io.bootique.RuntimeModuleMergerTest class30 = new io.bootique.RuntimeModuleMergerTest();
		class30.before();
		class30.testGetModules_Empty();
		class30.testGetModules_One();
		class30.testGetModules_Two();
		class30.testGetModules_Three_Dupes();
		class30.testGetModules_Overrides();
		class30.testGetModules_Overrides_Chain();
		class30.testGetModules_OverrideCycle();
		class30.testGetModules_OverrideIndirectCycle();
		class30.testGetModules_OverrideDupe();
		io.bootique.BootiqueStaticsTest class31 = new io.bootique.BootiqueStaticsTest();
		class31.testCreateModule();
		io.bootique.Bootique_DeclareVarsIT class32 = new io.bootique.Bootique_DeclareVarsIT();
		class32.testInHelp();
		class32.testInHelpDescription();
		class32.testInHelpWithMap();
		class32.testInHelpWithList();
		io.bootique.Bootique_Configuration_VarsIT class33 = new io.bootique.Bootique_Configuration_VarsIT();
		class33.testOverrideValue();
		class33.testOverrideValueArray();
		class33.testDeclareVar_ConfigPathCaseSensitivity();
		class33.testDeclareVar_NameCaseSensitivity();
		io.bootique.Bootique_ModuleOverridesIT class34 = new io.bootique.Bootique_ModuleOverridesIT();
		class34.testCreateInjector_Overrides();
		class34.testCreateInjector_Overrides_Multi_Level();
		class34.testCreateInjector_OverridesWithProvider();
		class34.testCreateInjector_Override_TwoLevelTree();
		class34.testCreateInjector_Override_ThreeLevelTree();
		class34.testCreateInjector_Overrides_OriginalModuleServices();
		class34.testCreateInjector_Overrides_Multi_Level_OriginalModuleServices();
		io.bootique.BQCoreModuleExtenderTest class35 = new io.bootique.BQCoreModuleExtenderTest();
		class35.testSetProperties();
		class35.testSetOptions();
		io.bootique.BQModuleIdTest class36 = new io.bootique.BQModuleIdTest();
		class36.testStaticClass();
		class36.testClass();
		class36.testMethodRef();
		class36.testLambda();
		class36.testLambdaFactory();
		class36.testMix();
		io.bootique.BaseModuleTest class37 = new io.bootique.BaseModuleTest();
		class37.testModule();
	}
}