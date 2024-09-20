# Moon Compiler

This is a compiler that was created for the class Comp 442. It takes as inoput a file in a made up language called moon (resembles JavaScript) and outputs machine code.

## How To Run

To compile a file, add it to `/Files/Source/`. Then go to `/src` and run `Driver.java`. This will produce a machine code file which can be found under `Files/Moon`.
To run the machine code, run the following command `/Files/Moon/a.out NAME_OF_FILE.m`.

## Features

-Lexer: Tokenizes the source code into a series of tokens for the parser.
-Parser: Converts tokens into an Abstract Syntax Tree (AST).
-Syntactic Analyzer: Checks the AST for syntatic errors, such as keyword misspelling.
-Semantic Analyzer: Checks the AST for semantic errors, such as type mismatches.
-Code Generator: Outputs machine code that can be executed by the target architecture.
-Tests: Unit tests for each stage of the compiler.
-Examples: Example programs to demonstrate the compiler's capabilities.
