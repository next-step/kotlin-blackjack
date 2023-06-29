package blackjack.card.signature

data class CardSignaturePack(
    val numberSignature: CardOrdinalSignature?,
    val symbolSignature: CardSymbolSignature?,
) {
    fun getName(): String {
        return numberSignature?.code + symbolSignature?.code
    }
}
