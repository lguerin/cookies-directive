// jQuery Plugin Cookies Directive
// version 1.0, October 6th, 2014
// by Laurent GUERIN
//= require jquery-cookie/jquery.cookie.min.js
;(function ($, window, document, undefined) {

    // Create the defaults once
    var pluginName = "cookiesDirective",
        defaults = {
            position: "top", // Position of the bar: top, bottom
            expires: 30, // Lifetime of the cookie, in days
            secure: false, // If true, the cookie transmission requires HTTPS
            domain: "", // The domain where the cookie is valid. Default: domain of page where the cookie was created
            path: "/" // Path where the cookie is valid. Default: '/'
        };

    // Plugin constructor
    function Plugin ( element, options ) {
        this.element = element;
        this.options = $.extend( {}, defaults, options );
        this.options.name = "cookies-directive"; // Default cookie name
        this._defaults = defaults;
        this._name = pluginName;
        this.init();
    }

    Plugin.prototype = {
        init: function () {
            this.checkCookie();
            var self = this;

            // Events
            $('.cookies-directive-button.accept').on('click', function(e) {
                self.acceptCookies();
            });
            $('.cookies-directive-button.decline').on('click', function(e) {
                self.declineCookies();
            });
        },

        _cookieOpts: function() {
            var opts = {
                expires: this.options.expires,
                secure: this.options.secure,
                path: this.options.path
            };

            // Domain option
            if (this.options.domain !== "") {
                opts.domain = this.options.domain;
            }

            return opts;
        },

        checkCookie: function () {
            var toDisplay = false;
            if (!$.cookie(this.options.name)) {
                this.createCookie(this.options);
                toDisplay = true;
            }
            else if ($.cookie(this.options.name) === "") {
                toDisplay = true;
            }

            if (toDisplay) {
                this.displayCookiesBar();
            }
        },

        createCookie: function() {
            var opts = this._cookieOpts();
            $.cookie(this.options.name, "", opts);
        },

        displayCookiesBar: function() {
            $(".cookies-directive").detach().prependTo("body");
            $(".cookies-directive").show();
        },

        hideCookiesBar: function() {
            $(".cookies-directive").hide();
        },

        setCookie: function(value) {
            window.location.replace('#');
            var opts = this._cookieOpts();
            $.cookie(this.options.name, value, opts);
            this.hideCookiesBar();
            return false;
        },

        acceptCookies: function() {
            this.setCookie('accepted');
        },

        declineCookies: function() {
            this.setCookie('declined');
        }
    };

    // Lightweight plugin wrapper around the constructor,
    // preventing against multiple instantiations
    $.fn[ pluginName ] = function ( options ) {
        return this.each(function() {
            if ( !$.data( this, "plugin_" + pluginName ) ) {
                $.data( this, "plugin_" + pluginName, new Plugin( this, options ) );
            }
        });
    };

})(jQuery, window, document);