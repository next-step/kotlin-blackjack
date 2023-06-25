package blackjack.card.signature

class CardSignaturePack(
    private val numberSignature: CardOrdinalSignature?,
    private val symbolSignature: CardSymbolSignature?,
) {
    fun getName(): String {
        return numberSignature?.code + symbolSignature?.code
    }
}
