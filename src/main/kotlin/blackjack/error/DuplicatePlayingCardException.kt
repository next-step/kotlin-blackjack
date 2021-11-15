package blackjack.error

import blackjack.domain.PlayingCard

class DuplicatePlayingCardException(private val playingCard: PlayingCard) : RuntimeException() {
    override val message = MESSAGE.format(playingCard.suit.name, playingCard.denomination.name)

    companion object {
        private const val MESSAGE = "'%s_%s'는 이미 덱에 존재하는 카드입니다."
    }
}
