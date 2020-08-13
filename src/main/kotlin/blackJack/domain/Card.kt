package blackJack.domain

data class Card(val shape: Shape, val denomination: Denomination) {
    fun getName(): String = shape.shape + denomination.symbol
}
