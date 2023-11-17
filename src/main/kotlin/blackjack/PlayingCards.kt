package blackjack

data class PlayingCards(val cards: List<PlayingCard>) {
    companion object {
        fun createCards(): PlayingCards {
            return PlayingCards(createPlayingCards())
        }

        private fun createPlayingCards(): List<PlayingCard> {
            return Suits.values().flatMap { suit ->
                CardNumber.values().map { num ->
                    PlayingCard(suit, num)
                }
            }
        }
    }
}
