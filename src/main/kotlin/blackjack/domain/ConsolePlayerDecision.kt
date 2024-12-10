package blackjack.domain

class ConsolePlayerDecision : PlayerDecision {
    override fun shouldDrawCard(playerName: String): Boolean {
        println(INPUT_PICK_CARD_MESSAGE.format(playerName))
        return readln().trim().lowercase() == "y"
    }

    companion object {
        private const val INPUT_PICK_CARD_MESSAGE = "%s는 한장의 카드를 더 받겠습니까? (예는 y, 아니오는 n)"
    }
}