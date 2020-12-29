import com.knoldus.config.app.Student
import monix.eval.Coeval

import scala.util.{Failure, Random, Success}


def average(students: List[Student]): Int = {
  println("Evaluating....\n")
  val average = students.map(_.marks).sum / students.size
  average
}

def students: List[Student] = (0 until 100).map{ rollNo =>
  Student(rollNo, Random.nextInt(100))
}.toList


// ********** Try ***********

val averageMarks: Coeval[Int] = Coeval(average(students))


val res: Coeval.Eager[Int] = averageMarks.run()

// ********** With exceptions *********

val error: Coeval[Int] = Coeval.raiseError[Int](new Exception("Test exception.."))

error.runTry match {
  case Success(value) =>
    println(value)
  case Failure(ex) =>
    println("Got error : " + ex)
}
