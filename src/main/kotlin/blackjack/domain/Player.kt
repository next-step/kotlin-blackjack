package blackjack.domain

import blackjack.domain.card.Card

class Player(private val name: String) : BlackJackGamer {
    private val cards = mutableListOf<Card>()
    private val cardNumberCalculator = CardNumberCalculator(GamerType.PLAYER)
    private var gameRecord: GameRecordType = GameRecordType.NONE

    override fun addCard(card: Card) {
        cards.add(card)
    }

    override fun addCards(drawCards: List<Card>) {
        cards.addAll(drawCards)
    }

    override fun getCards(): List<Card> {
        return cards.toList()
    }

    override fun calculateSumOfCardNumbers(): Int {
        return cardNumberCalculator.calculateSumOfCardNumbers(cards)
    }

    override fun getGamerType(): GamerType {
        return GamerType.PLAYER
    }

    override fun getName(): String {
        return name
    }

    override fun proceedGameRecord(gameRecordType: GameRecordType) {
        gameRecord = gameRecordType
    }

    fun getGameRecord(): GameRecordType {
        require(gameRecord != GameRecordType.NONE) { "승패가 결정난 뒤에 조회가 가능합니다." }
        return gameRecord
    }

    companion object {
        fun generatePlayers(nameList: List<String>): List<Player> {
            val playerList = mutableListOf<Player>()
            nameList.forEach {
                playerList.add(Player(it))
            }
            return playerList.toList()
        }
    }
}
