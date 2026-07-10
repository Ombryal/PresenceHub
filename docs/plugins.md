# Plugins

Plugins are distributed independently from the main application.

Every plugin must

- implement PresencePlugin
- target the supported Plugin API version
- provide metadata
- pass checksum verification
- optionally include a digital signature

The core application never contains provider-specific detection code.
