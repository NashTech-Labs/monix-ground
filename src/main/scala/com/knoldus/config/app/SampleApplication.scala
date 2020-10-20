package com.knoldus.config.app

// On evaluation a Scheduler is needed
import monix.execution.CancelableFuture
import monix.execution.Scheduler.Implicits.global

import scala.collection.immutable
import scala.util.Random
// For Task
import monix.eval._
// For Observable
import monix.reactive._

case class Student(rollNo: Int, marks: Int)


object SampleApplication extends App {

  def average(students: List[Student]): Int = {
    students.map(_.marks).sum / students.size
  }

  val students: List[Student] = (0 until 100).map{ rollNo =>
    Student(rollNo, Random.nextInt(100))
  }.toList

  val averageMarksTask: Task[Int] = Task(average(students))

  val result = averageMarksTask.runAsync{
    case Right(value) => println("Value.... " + value)
    case Left(ex) => println("Error....." + ex)
  }

  val fut: CancelableFuture[Int] = averageMarksTask.runToFuture

  println("Fut....." + fut)

//  println("Average marks... " + averageMarks)

  // The list of all tasks needed for execution
//  val tasks: immutable.Seq[Task[Int]] = items.map(i => Task(i * 2 / 2))
  // Processing in parallel
//  val aggregate: Task[immutable.Seq[Int]] = Task.parSequence(tasks)


  // Evaluation:
  /*val resp: Task[immutable.Seq[Int]] = aggregate.map{ x =>
    println("=====+++>>>>>>>" + x)
    x.map(_ + 1)
  }*/

//  resp.foreach(x => println("........... " + x))


//  Task.future





}
