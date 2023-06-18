package blackjack.domain

class FaceCard(override val symbol: SymbolType, private val faceType: FaceType) : Card {
    override val priority: Int = 2

    override fun name(): String = faceType.name.first().toString()

    override fun score(acc: Int): Int = FACE_SCORE

    companion object {
        private const val FACE_SCORE = 10
    }
}
