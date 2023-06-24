package blackjack.view

object ResultView {
    fun printUserCardList(userName: String, cardList: List<String>){
        var result = cardList.joinToString(", ")
        println("${userName}카드: $result")
    }
}
