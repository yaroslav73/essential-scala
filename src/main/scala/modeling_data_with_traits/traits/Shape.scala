package modeling_data_with_traits.traits

import modeling_data_with_traits.sealed_traits.Color

sealed trait Shape {
  def sides: Int

  def perimeter: Double

  def area: Double

  def color: Color
}

sealed trait Rectangular extends Shape {
  def width: Double

  def height: Double

  override def sides: Int = 4

  override def perimeter: Double = (width + height) * 2

  override def area: Double = width * height
}

final case class Circle(radius: Double, color: Color) extends Shape {
  override def sides: Int = 1

  override def perimeter: Double = 2 * Math.PI * radius

  override def area: Double = Math.PI * Math.pow(radius, 2)
}

final case class Rectangle(width: Double, height: Double, color: Color) extends Rectangular

final case class Square(size: Double, color: Color) extends Rectangular {
  override def width: Double = size

  override def height: Double = size
}