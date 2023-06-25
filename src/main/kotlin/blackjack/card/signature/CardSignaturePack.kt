package blackjack.card.signature

class CardSignaturePack(
    val numberSignature: CardOrdinalSignature?,
    val symbolSignature: CardSymbolSignature?,
) {
    fun getName(): String {
        return numberSignature?.code + symbolSignature?.code
    }
}
