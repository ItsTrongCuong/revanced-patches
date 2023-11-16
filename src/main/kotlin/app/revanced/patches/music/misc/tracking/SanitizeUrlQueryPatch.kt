package app.revanced.patches.music.misc.tracking

import app.revanced.patcher.data.BytecodeContext
import app.revanced.patcher.patch.annotation.CompatiblePackage
import app.revanced.patcher.patch.annotation.Patch
import app.revanced.patches.music.misc.tracking.fingerprints.ShareLinkFormatterFingerprint
import app.revanced.patches.music.utils.settings.SettingsPatch
import app.revanced.patches.shared.fingerprints.tracking.CopyTextEndpointFingerprint
import app.revanced.patches.shared.patch.tracking.AbstractSanitizeUrlQueryPatch
import app.revanced.util.enum.CategoryType
import app.revanced.util.integrations.Constants.MUSIC_MISC_PATH

@Patch(
    name = "Sanitize sharing links",
    description = "Removes tracking query parameters from the URLs when sharing links.",
    dependencies = [SettingsPatch::class],
    compatiblePackages = [
        CompatiblePackage(
            "com.google.android.apps.youtube.music",
            [
                "6.15.52",
                "6.20.51",
                "6.26.51",
                "6.27.53"
            ]
        )
    ]
)
@Suppress("unused")
object SanitizeUrlQueryPatch : AbstractSanitizeUrlQueryPatch(
    "$MUSIC_MISC_PATH/SanitizeUrlQueryPatch;",
    listOf(
        CopyTextEndpointFingerprint,
        ShareLinkFormatterFingerprint
    ),
    null
) {
    override fun execute(context: BytecodeContext) {
        super.execute(context)

        SettingsPatch.addMusicPreference(
            CategoryType.MISC,
            "revanced_sanitize_sharing_links",
            "true"
        )

    }
}
