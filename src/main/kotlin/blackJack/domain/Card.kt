package blackJack.domain

data class Card(val shape: Shape, val denomination: Denomination) {
    override fun toString() = "${shape.shape}${denomination.symbol}"
}
