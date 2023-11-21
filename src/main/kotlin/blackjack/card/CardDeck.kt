package blackjack.card

import java.util.ArrayDeque

class CardDeck {
    init {
        initNormalCard()
        initPictureCard()
        initAceCard()
        deck.shuffled()
    }

    fun getSize(): Int {
        return deck.size
    }

    companion object {
        private val deck: ArrayDeque<BlackJackCard> = ArrayDeque()
        private fun initNormalCard() {
            CardPattern.values().forEach {
                (2..10).forEach { number -> deck.add(NormalCard(number, it)) }
            }
        }

        private fun initPictureCard() {
            CardPattern.values().forEach {
                CardPicture.values().forEach { picture -> deck.add(PictureCard(picture, it)) }
            }
        }

        private fun initAceCard() {
            CardPattern.values().forEach {
                deck.add(AceCard(it))
            }
        }
    }
}
