# Ray Tracer in Java

* Current version: 0.5
* Build environment: mac OS X 10.11, IntelliJ IDEA 2016.2.4

## Introduction

This is a simple ray tracer implemented in Java 8, followed the instructions from the book *Ray Tracer from the Ground
Up*. The original code was C++. To practice my Java skill I choose to translate and improve the original one. In fact,
Java is not a good choice for this task, for the sake of efficiency and elegance (Java do not support operator
overloading hence it's super ugly to implement a Math library in Java).

## Current Process

* Sep 28, 2016: Working on `Sampler` classes. Correlation with `World` and `ViewPlane` needs to be fixed.
* Oct 1, 2016: Beginning to write rendering methods with materials, lights, and BRDFs.
* Oct 1, 2016: Writing unit tests for all classes.
* Oct 15, 2016: Now it works. The following work is to improve samplers, brdfs, and find a good way to test each modulo efficiently.

## Style List

### Getters and setters

Except these fundamental classes in `utilities`, including `Point2D`, `Point3D`, `Vector3D`, `Normal`, and `RgbColor`,
all data fields should be private or package-private, and should be accessed/modified by getters/setters. The basic
guide line is to make all classes manageable.

## TODO List

* Improving all classes' constructors, especially providing new builder factory constructors.
* Maintenance of getters and setters.
