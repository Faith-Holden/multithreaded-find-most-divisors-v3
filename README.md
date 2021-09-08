# multithreaded-find-most-divisors-v3
My solution for Chapter 12 Exercise 4 of “Introduction to Programming Using Java”.

Problem Description:
In previous exercise, you used a thread pool and a queue of tasks to find the integer in the
range 1 to 100000 that has the largest number of divisors. Subsection 12.3.4 discusses a
higher-level approach that uses an ExecutorService. Write one more program to solve the
problem, this time using an ExecutorService and Futures. The program should still break
up the computation into a fairly large number of fairly small tasks, and it should still print
out the largest number of divisors and the integer that has that number of divisors.
(There is yet another way to solve the same problem: the stream API from Section 10.6.
My solution using the stream API, however, uses an aspect of the stream API that I did
not cover: the interface Optional<T>. My on-line solution of this exercise also discusses
how to use streams to solve the problem.)
