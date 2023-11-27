package blackjack.domain.model

class Cards(val cards: List<Card>) {
    fun scores(): List<Score> {
        check(cards.isNotEmpty()) { "카드의 개수가 0장으로 점수를 계산할 수 없습니다." }

        return cards
            .drop(1)
            .fold(cards.getFirstCardScores()) { scores, card -> calculatePossibleScores(card, scores) }
            .toList()
            .sortedBy { it.score }
    }

    private fun List<Card>.getFirstCardScores(): Set<Score> {
        return first().scores.toSet()
    }

    private fun calculatePossibleScores(card: Card, scores: Set<Score>): Set<Score> {
        return card.scores
            .map { scores.map { score -> score + it } }
            .flatten()
            .toSet()
    }

    companion object {
        fun create(): Cards {
            val numbers = CardNumber.values()
            val shapes = CardShape.values()

            return shapes
                .map { shape -> numbers.map { number -> Card.of(number, shape) } }
                .flatten()
                .run { Cards(this) }
        }

        fun of(vararg cards: Card): Cards {
            return Cards(cards.toList())
        }

        fun of(cards: List<Card>): Cards {
            return Cards(cards)
        }
    }
}
