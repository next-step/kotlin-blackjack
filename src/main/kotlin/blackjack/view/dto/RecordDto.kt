package blackjack.view.dto

data class RecordDto(
    val dealerName: String,
    val records: Map<String, Boolean>,
)
