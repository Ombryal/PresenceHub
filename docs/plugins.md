# Plugins

Plugins are distributed independently from the main application.

Every plugin must

- implement PresencePlugin
- target the supported Plugin API version
- provide metadata
- pass checksum verification
- optionally include a digital signature

The core application never contains provider-specific detection code.

Each plugin is distributed as its own package artifact, usually an APK, hosted in the plugin repository. The main app downloads the plugin index, reads plugin package metadata, verifies the package, and then installs or activates it.
