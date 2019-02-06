package ca.csf.mobile2.tp2.question

//BEN_REVIEW : Vous respectez effectivement les standards du langage
//             (voir https://kotlinlang.org/docs/reference/coding-conventions.html#property-names),
//             mais c'est clairement une habitude qui vous vient du C#. Juste faire attention.
//
//BEN_CORRECTION : Par contre, ce qui m'énerve ici, c'est le double standard. Ici, c'est du PascalCase,
//                 mais dans "ErrorCode", c'est du UPPER_CASE. J'ai donc pénalisé quand même pour le non
//                 respect des standards du langage.
enum class AppState {
    MakeChoice,
    ChoiceResult,
    Error,
    Fetch,
}