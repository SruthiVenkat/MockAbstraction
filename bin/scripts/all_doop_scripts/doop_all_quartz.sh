#!/bin/bash

# doop runs for quartz
BENCHMARK="quartz-quartz-2.3.1-patched"
RESULT="quartz-core-2.3.1"

for base_analysis in basic-only context-insensitive context-insensitive-plusplus; do
  if [[ "$base_analysis" == "basic-only" ]]; then
    ./doop -a $base_analysis -i $HOME/Benchmarks/$BENCHMARK/quartz-core/target/$RESULT.jar -i $HOME/Benchmarks/$BENCHMARK/quartz-core/target/$RESULT-tests.jar -i $HOME/Benchmarks/$BENCHMARK/quartz-core/mvn_dependencies --id quartz-core-$base_analysis-NORMAL --souffle-jobs 32 --main org.quartz.RootDriver --define-cpp-macro USE_BASIC --extra-logic souffle-logic/analyses/mocks/mocks.dl &> $HOME/results/quartz-core-results/quartz-core-$base_analysis-NORMAL.log

    for n in NORMAL NO_INTERPROC; do
      mkdir -p results/$RESULT/$base_analysis/java_8/

      rm -rf results/$RESULT/$base_analysis/java_8/quartz-core-$base_analysis-$n

      cp -a out/quartz-core-$base_analysis-NORMAL/database/* results/$RESULT/$base_analysis/java_8/quartz-core-$base_analysis-$n

      /usr/bin/time -o $HOME/souffle-$base_analysis-$BENCHMARK-$n souffle -F results/$RESULT/$base_analysis/java_8/quartz-core-$base_analysis-$n/ -M $n souffle-logic/analyses/mocks/mocks-after.dl -D results/$RESULT/$base_analysis/java_8/quartz-core-$base_analysis-$n/

      ./count.py --file results/$RESULT/$base_analysis/java_8/quartz-core-$base_analysis-$n/isMockInvocation.csv &> $HOME/results/quartz-core-results/$base_analysis-counts-$n
    done
  else
    ./doop -a $base_analysis -i $HOME/Benchmarks/$BENCHMARK/quartz-core/target/$RESULT.jar -i $HOME/Benchmarks/$BENCHMARK/quartz-core/target/$RESULT-tests.jar -i $HOME/Benchmarks/$BENCHMARK/quartz-core/mvn_dependencies --id quartz-core-$base_analysis-NORMAL --souffle-jobs 32 --main org.quartz.RootDriver --define-cpp-macro USE_CALLGRAPH --extra-logic souffle-logic/analyses/mocks/mocks.dl &> $HOME/results/quartz-core-results/quartz-core-$base_analysis-NORMAL.log

    for n in NORMAL NO_INTERPROC; do
      mkdir -p results/$RESULT/$base_analysis/java_8/

      rm -rf results/$RESULT/$base_analysis/java_8/quartz-core-$base_analysis-$n

      cp -a out/quartz-core-$base_analysis-NORMAL/database/* results/$RESULT/$base_analysis/java_8/quartz-core-$base_analysis-$n

      /usr/bin/time -o $HOME/souffle-$base_analysis-$BENCHMARK-$n souffle -F results/$RESULT/$base_analysis/java_8/quartz-core-$base_analysis-$n/ -M $n souffle-logic/analyses/mocks/mocks-after.dl -D results/$RESULT/$base_analysis/java_8/quartz-core-$base_analysis-$n/

      ./count.py --file results/$RESULT/$base_analysis/java_8/quartz-core-$base_analysis-$n/isMockInvocation.csv &> $HOME/results/quartz-core-results/$base_analysis-counts-$n
    done
  fi
done
