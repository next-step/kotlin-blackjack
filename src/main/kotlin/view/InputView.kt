package view

import domain.Card
import domain.Player

object InputView {

    fun getUserName(): List<Player> {
        displayUserNameInput()
        val userNames = readln().split(DELIMITER)

        return userNames.map { Player(it) }
    }

    fun setBattingAmount(player: Player) {
        displayBattingAmount(player.name)
        val battingAmount = readln().toInt()

        player.battingAmount = battingAmount
        println()
    }

    private fun displayUserNameInput() {
        println(PLAYER_INPUT)
    }

    private fun displayBattingAmount(name: String) {
        println(PLAYER_BATTING_AMOUNT_INPUT.format(name))
    }

    private fun displayYesOrNo(name: String) {
        println(YES_NO_MESSAGE.format(name))
    }

    fun displayCardDivide(player: String, count: String) {
        println(CARD_DIVIDE_MESSAGE.format(player, count))
    }

    fun isYesOrNo(name: String): Boolean {
        displayYesOrNo(name)
        var input = readln()
        return enableIssued(input)
    }

    private fun enableIssued(input: String): Boolean {
        return try {
            ensureValidAskConditionInput(input)
            return input.equals(YES)
        } catch (e: IllegalArgumentException) {
            println(YES_NO_INVALID_MESSAGE)
            return false
        }
    }

    private fun ensureValidAskConditionInput(isChecked: String) {
        require(isChecked == YES || isChecked == NO)
    }

    fun displayHaveCard(player: Player) {
        println(player.name + " : " + getCardString(player.cards))
    }

    fun getCardString(cards: List<Card>): String {
        val formattedStrings = cards.map { card -> "${card.suit.suitName}${card.denomination.denominationName}" }
        return formattedStrings.joinToString(DELIMITER)
    }

    fun displayDealerCard() {
        println()
        println(DEALER_CARD_MESSAGE)
    }

    private const val YES_NO_MESSAGE = "%s는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)"
    private const val YES_NO_INVALID_MESSAGE = "y나 n만 입력해주세요"
    private const val DELIMITER = ","
    private const val PLAYER_INPUT = "게임에 참여할 사람의 이름을 입력하세요. (쉼표 기준으로 분리)"
    private const val PLAYER_BATTING_AMOUNT_INPUT = "%s의 배팅 금액은?"
    private const val CARD_DIVIDE_MESSAGE = "%s에게 %s장의 카드를 나누었습니다."
    private const val DEALER_CARD_MESSAGE = "딜러는 16이하라 한장의 카드를 더 받았습니다."

    private const val YES = "y"
    private const val NO = "n"
}
