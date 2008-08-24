package com.synergyj.grails.plugins.avatar

import com.synergyj.grails.plugins.avatar.util.MD5Util

/* For now: locally modified version which uses different base url and accepts the default gravatar url attribute */
class AvatarTagLib {
	static namespace = "avatar"
	
	def gravatar = { attrs, body ->
        def email = attrs.email
		def size = 20
        def hash = MD5Util.md5Hex(email)
		def alt = "Gravatar"
		def cssClass = "avatar"
		def gravatarBaseUrl = "https://secure.gravatar.com/avatar/"
		def gravatarUrl = "$gravatarBaseUrl$hash"

		if(attrs.size) {
			size = attrs.size
		}
		
		if(attrs.alt) {
			alt = attrs.alt
		}
		
		if(attrs.cssClass) {
			cssClass = attrs.cssClass
		}

		if(attrs.defaultGravatarUrl) {
			gravatarUrl += "?d=${attrs.defaultGravatarUrl}"
		}

		
		
        out << """
			<img alt="$alt" class="$cssClass" height="$size" src="$gravatarUrl" width="$size" />
		"""
	}
}
