package blackJack.domain

class Card(private val shape: Shape, private val denomination: Denomination) {
    override fun toString(): String = "${shape.shape}${denomination.symbol}"
}
