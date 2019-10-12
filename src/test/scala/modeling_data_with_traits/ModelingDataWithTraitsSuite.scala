package modeling_data_with_traits

import modeling_data_with_traits.algebraic_data_types.{Calculation, Calculator, Failure, Success}
import modeling_data_with_traits.recursive_data.{End, IntListOps, Pair}
import modeling_data_with_traits.sealed_traits._
import modeling_data_with_traits.traits._
import org.scalatest.{FunSuite, Matchers}

class ModelingDataWithTraitsSuite extends FunSuite with Matchers {
  test("Cat sound should be 'meow' and other Feline should be 'roar'") {
    val cat = Cat("white", "chips")
    val lion = Lion("gray", 7)
    val tiger = Tiger("black and yellow")
    val panther = Panther("black")

    cat.sound shouldBe "meow"
    lion.sound shouldBe "roar"
    tiger.sound shouldBe "roar"
    panther.sound shouldBe "roar"
  }

  test("Shape subtypes should be correct evaluate perimeter and area") {
    val customColor = CustomColor(3, 3, 3)
    val circle = Circle(5, customColor)
    val square = Square(7, customColor)
    val rectangle = Rectangle(5, 7, customColor)

    circle.perimeter shouldBe 2 * 5 * Math.PI
    circle.area shouldBe 5 * 5 * Math.PI

    square.perimeter shouldBe 4 * 7
    square.area shouldBe 7 * 7

    rectangle.perimeter shouldBe (5 + 7) * 2
    rectangle.area shouldBe 5 * 7
  }

  test("Draw object should return appropriate shapes describe") {
    val circle = Circle(5, CustomColor(3, 3, 3))
    val square = Square(7, Yellow)
    val rectangle = Rectangle(5, 7, Green)

    Draw(circle) shouldBe "A light circle with radius 5.0cm"
    Draw(square) shouldBe "A yellow square with size 7.0cm"
    Draw(rectangle) shouldBe "A green rectangle of width 5.0cm and height 7.0cm"
  }

  test("divide(3, 2) should be Finite(1.5)") {
    divide(3, 2) shouldBe Finite(1.5)
  }

  test("divide(2, 0) should be Infinite") {
    divide(2, 0) shouldBe Infinite
  }

  test("plus (+) method should return Success with result if in argument passed Success") {
    Calculator.+(Success(10), 7) shouldBe Success(17)
  }

  test("plus (+) method should return Failure with message if in argument passed Failure") {
    Calculator.+(Failure("oh no..."), 10) shouldBe Failure("oh no...")
  }

  test("minus (-) method should return Success with result if in argument passed Success") {
    Calculator.-(Success(10), 10) shouldBe Success(0)
  }

  test("minus (-) method should return Failure with message if in argument passed Failure") {
    Calculator.-(Failure("oh no..."), 10) shouldBe Failure("oh no...")
  }

  test("division (/) method should return Success with result if in argument passed Success") {
    Calculator./(Success(20), 10) shouldBe Success(2)
  }

  test("division (/) method should return Failure with message if in argument passed Failure") {
    Calculator./(Failure("oh no..."), 10) shouldBe Failure("oh no...")
  }

  test("division (/) method should return Failed(division by zero) if divisor is 0") {
    Calculator./(Success(10), 0) shouldBe Failure("division by zero")
  }

  test("TrafficLight's action method should be appropriate action by color") {
    import modeling_data_with_traits.algebraic_data_types.{Green, Red, Yellow}

    val red = Red
    val yellow = Yellow
    val green = Green

    red.action shouldBe "stop"
    yellow.action shouldBe "ready"
    green.action shouldBe "go"
  }

  test("TrafficLight's next method should be appropriate next light") {
    import modeling_data_with_traits.algebraic_data_types.{Green, Red, Yellow}

    val red = Red
    val yellow = Yellow
    val green = Green

    red.next shouldBe Yellow
    yellow.next shouldBe Green
    green.next shouldBe Red
  }

  test("divide method from Calculation should return Succeed or Fail") {
    Calculation.divide(4, 2) shouldBe Success(2)

    Calculation.divide(7, 0) shouldBe Failure("division by zero")
  }

  test("Structural recursion using polymorphism dinner method should be return food") {
    val cat = Cat("white", "chips")
    val lion = Lion("gray", 7)
    val tiger = Tiger("black and yellow")
    val panther = Panther("black")

    cat.dinner shouldBe CatFood("chips")
    lion.dinner shouldBe Antelope
    tiger.dinner shouldBe TigerFood
    panther.dinner shouldBe Licorice
  }

  test("Structural recursion using pattern matching dinner method should be return food") {
    val cat = Cat("white", "chips")
    val lion = Lion("gray", 7)
    val tiger = Tiger("black and yellow")
    val panther = Panther("black")

    cat.dinners shouldBe CatFood("chips")
    lion.dinners shouldBe Antelope
    tiger.dinners shouldBe TigerFood
    panther.dinners shouldBe Licorice
  }

  test("sum should return sum of entire IntList") {
    val list = Pair(1, Pair(2, Pair(3, Pair(4, Pair(5, End)))))

    IntListOps.sum(list) shouldBe 15
    IntListOps.sum(list.tail) shouldBe 14
    IntListOps.sum(End) shouldBe 0
  }

  test("length should return appropriate IntList length") {
    val list = Pair(1, Pair(2, Pair(3, Pair(4, Pair(5, End)))))

    list.length shouldBe 5
    list.tail.length shouldBe 4
    End.length shouldBe 0
  }

  test("product should return product of entire IntList") {
    val list = Pair(1, Pair(2, Pair(3, Pair(4, Pair(5, End)))))

    IntListOps.product(list) shouldBe 120
    IntListOps.product(list.tail) shouldBe 120
    IntListOps.product(End) shouldBe 1
  }

  test("double should return IntList with doubled entire") {
    val list = Pair(1, Pair(2, Pair(3, Pair(4, Pair(5, End)))))

    IntListOps.double(list) shouldBe Pair(2, Pair(4, Pair(6, Pair(8, Pair(10, End)))))
    IntListOps.double(list.tail) shouldBe Pair(4, Pair(6, Pair(8, Pair(10, End))))
    IntListOps.double(End) shouldBe End
  }
}