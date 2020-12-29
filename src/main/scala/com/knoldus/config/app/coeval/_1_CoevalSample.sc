import com.knoldus.config.app.Student
import monix.eval.Coeval
import scala.util.{Success, Failure}
import scala.util.Random


def average(students: List[Student]): Int = {
  println("Evaluating....\n")
  val average = students.map(_.marks).sum / students.size
  average
}

def students: List[Student] = (0 until 100).map{ rollNo =>
  Student(rollNo, Random.nextInt(100))
}.toList


val averageNormal: Int = average(students)

// **** Coeval declaration *******

val coeval: Coeval[Int] = Coeval(average(students))

// Coeval has lazy behavior, so nothing
// happens until being evaluated:
coeval.value
