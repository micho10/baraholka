#!/usr/bin/env bash

spark-submit --class "SparkSQLExample" --master local[4] target/scala-2.11/baraholka_2.11-0.1.jar
