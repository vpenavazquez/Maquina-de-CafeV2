package main.org.example

enum class Bebida(
    val id: Int,
    val nombre: String,
    val precio: Double,
    var cantidad: Int
) {
    CAFE_SOLO(1, "Café Solo", 1.20, 5),
    CAFE_CON_LECHE(2, "Café con Leche", 1.50, 8),
    NO_CAFE(3, "No café", 1.80, 0);
}