package blackjack.domain

data class Card(val shape: Shape, val character: Character) {
    companion object {
        private val gameCards: MutableList<Pair<Shape, Character>> = mutableListOf()
        fun draw(): Card {
            var card = Card(getShape(), getChar())
            while (gameCards.contains(card.shape to card.character)) {
                card = Card(getShape(), getChar())
            }
            gameCards.add(Pair(card.shape, card.character))
            return card
        }

        private fun getShape(): Shape {
            return Shape.values().random()
        }

        private fun getChar(): Character {
            return Character.values().random()
        }
    }
}
