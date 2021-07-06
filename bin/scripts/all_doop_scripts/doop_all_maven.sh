#!/bin/bash

# doop runs for maven
BENCHMARK="maven-maven-3.8.1"
RESULT="maven-core-3.8.1"

for base_analysis in basic-only context-insensitive context-insensitive-plusplus; do
  if [[ "$base_analysis" == "basic-only" ]]; then
    ./doop -a $base_analysis -i $HOME/Benchmarks/$BENCHMARK/maven-core/target/$RESULT.jar -i $HOME/Benchmarks/$BENCHMARK/maven-core/target/$RESULT-tests.jar -i $HOME/Benchmarks/$BENCHMARK/maven-core/mvn_dependencies --id maven-core-$base_analysis-NORMAL --souffle-jobs 32 --main org.apache.maven.RootDriver --define-cpp-macro USE_BASIC --extra-logic souffle-logic/analyses/mocks/mocks.dl &> $HOME/results/maven-core-results/maven-core-$base_analysis-NORMAL.log

    for n in NORMAL NO_INTERPROC; do
      mkdir -p results/$RESULT/$base_analysis/java_8/

      rm -rf results/$RESULT/$base_analysis/java_8/maven-core-$base_analysis-$n

      cp -a out/maven-core-$base_analysis-NORMAL/database/* results/$RESULT/$base_analysis/java_8/maven-core-$base_analysis-$n

      /usr/bin/time -o $HOME/souffle-$base_analysis-$BENCHMARK-$n souffle -F results/$RESULT/$base_analysis/java_8/maven-core-$base_analysis-$n/ -M $n souffle-logic/analyses/mocks/mocks-after.dl -D results/$RESULT/$base_analysis/java_8/maven-core-$base_analysis-$n/

      ./count.py --file results/$RESULT/$base_analysis/java_8/maven-core-$base_analysis-$n/isMockInvocation.csv &> $HOME/results/maven-core-results/$base_analysis-counts-$n
    done
  else
    ./doop -a $base_analysis -i $HOME/Benchmarks/$BENCHMARK/maven-core/target/$RESULT.jar -i $HOME/Benchmarks/$BENCHMARK/maven-core/target/$RESULT-tests.jar -i $HOME/Benchmarks/$BENCHMARK/maven-core/mvn_dependencies --id maven-core-$base_analysis-NORMAL --souffle-jobs 32 --main org.apache.maven.RootDriver --define-cpp-macro USE_CALLGRAPH --extra-logic souffle-logic/analyses/mocks/mocks.dl &> $HOME/results/maven-core-results/maven-core-$base_analysis-NORMAL.log

    for n in NORMAL NO_INTERPROC; do
      mkdir -p results/$RESULT/$base_analysis/java_8/

      rm -rf results/$RESULT/$base_analysis/java_8/maven-core-$base_analysis-$n

      cp -a out/maven-core-$base_analysis-NORMAL/database/* results/$RESULT/$base_analysis/java_8/maven-core-$base_analysis-$n

      /usr/bin/time -o $HOME/souffle-$base_analysis-$BENCHMARK-$n souffle -F results/$RESULT/$base_analysis/java_8/maven-core-$base_analysis-$n/ -M $n souffle-logic/analyses/mocks/mocks-after.dl -D results/$RESULT/$base_analysis/java_8/maven-core-$base_analysis-$n/

      ./count.py --file results/$RESULT/$base_analysis/java_8/maven-core-$base_analysis-$n/isMockInvocation.csv &> $HOME/results/maven-core-results/$base_analysis-counts-$n
    done
  fi
done
