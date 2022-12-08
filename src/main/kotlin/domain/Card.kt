package domain

data class Card(val number: CardNumber, val shape: CardShape) {

    operator fun plus(score: Int): Int = this.number.score + score
}
