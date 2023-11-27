package blackjack.card

object CardDeck {
    private val deck: ArrayDeque<BlackJackCard>
    private const val EMPTY_DECK_ERROR_MESSAGE: String = "카드 덱이 모두 소진되었습니다."
    private const val FIRST_NORMAL_CARD_NUMBER: Int = 2
    private const val LAST_NORMAL_CARD_NUMBER: Int = 10

    init {
        val cards: List<BlackJackCard> = normalCard() + pictureCard() + aceCard()
        deck = ArrayDeque(cards.shuffled())
    }

    fun getSize(): Int {
        return deck.size
    }

    fun draw(drawNumber: Int): List<BlackJackCard> {
        require(deck.size >= drawNumber) { EMPTY_DECK_ERROR_MESSAGE }
        return (1..drawNumber).map { deck.removeFirst() }.toList()
    }

    private fun initNormalCard(list: MutableList<BlackJackCard>) {
        CardPattern.values().forEach {
            (FIRST_NORMAL_CARD_NUMBER..LAST_NORMAL_CARD_NUMBER).forEach { number -> list.add(NormalCard(number, it)) }
        }
    }

    private fun normalCard(): List<BlackJackCard> {
        return CardPattern.values().flatMap {
            (FIRST_NORMAL_CARD_NUMBER..LAST_NORMAL_CARD_NUMBER).map { number -> NormalCard(number, it) }
        }
    }

    private fun pictureCard(): List<BlackJackCard> {
        return CardPattern.values().flatMap {
            CardPicture.values().map { picture -> PictureCard(picture, it) }
        }
    }

    private fun aceCard(): List<BlackJackCard> {
        return CardPattern.values().map(::AceCard)
    }

    private fun initPictureCard(list: MutableList<BlackJackCard>) {
        CardPattern.values().forEach {
            CardPicture.values().forEach { picture -> list.add(PictureCard(picture, it)) }
        }
    }

    private fun initAceCard(list: MutableList<BlackJackCard>) {
        CardPattern.values().forEach {
            list.add(AceCard(it))
        }
    }
}
