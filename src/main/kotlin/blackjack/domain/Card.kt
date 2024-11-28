package blackjack.domain

class Card {
    val cardList = mutableListOf<String>()
    val cardShape = listOf("하트", "클로버", "스페이드", "다이아")
    val cardNumber = listOf("A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K")

    fun createCard() {
        for (shape in cardShape) {
            for (number in cardNumber) {
                cardList.add(number + shape)
            }
        }
    }
}