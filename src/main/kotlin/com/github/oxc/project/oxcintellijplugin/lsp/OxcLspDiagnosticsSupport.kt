package com.github.oxc.project.oxcintellijplugin.lsp

import com.intellij.openapi.diagnostic.thisLogger
import com.intellij.platform.lsp.api.customization.LspDiagnosticsSupport
import org.eclipse.lsp4j.Diagnostic

@Suppress("UnstableApiUsage")
class OxcLspDiagnosticsSupport : LspDiagnosticsSupport() {

    override fun getMessage(diagnostic: Diagnostic): String {
        thisLogger().debug("Creating message for diagnostic: $diagnostic")
        return "${diagnostic.source}: ${diagnostic.message} ${diagnostic.code.get()}"
    }

    override fun getTooltip(diagnostic: Diagnostic): String {
        thisLogger().debug("Creating tooltip for diagnostic: $diagnostic")
        var rule = diagnostic.code.get()
        if (diagnostic.codeDescription?.href != null) {
            rule = "<a href=\"${diagnostic.codeDescription.href}\">${diagnostic.code.get()}</a>"
        }

        return "${diagnostic.source}: ${diagnostic.message} $rule"
    }
}
