# Ray Tracer in Java

* Current version: 0.5

## Introduction

This is a simple ray tracer implemented in Java 8, followed the instructions from the book *Ray Tracer from the Ground
Up*. The original code was C++. To practice my Java skill I choose to translate and improve the original one. In fact,
Java is not a good choice for this task, for the sake of efficiency and elegance (Java do not support operator
overloading hence it's super ugly to implement a Math library in Java).

## Current Process

* Sep 28, 2016: Working on `Sampler` classes. Correlation with `World` and `ViewPlane` needs to be fixed.

## TODO List

* Improving all classes' constructors, especially providing new builder factory constructors.