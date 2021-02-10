#!/usr/bin/env bash

mvn package

docker build -t demo:latest .