/*!
 * Star Rating French Translations
 *
 * This file must be loaded after 'star-rating.js'. Patterns in braces '{}', or
 * any HTML markup tags in the messages must not be converted or translated.
 *
 * @see http://github.com/kartik-v/bootstrap-star-rating
 * @author Kartik Visweswaran <kartikv2@gmail.com>
 *
 * NOTE: this file must be saved in UTF-8 encoding.
 */
(function ($) {
    "use strict";
    $.fn.ratingLocales['fr'] = {
        defaultCaption: '{rating} étoiles',
        starCaptions: {
            0.5: 'Une demi étoile',
            1: '1 point',
            1.5: 'Une étoile et demi',
            2: '2 points',
            2.5: 'Deux étoiles et demi',
            3: '3 points',
            3.5: 'Trois étoiles et demi',
            4: '4 points',
            4.5: 'Quatre étoiles et demi',
            5: '5 points'
        },
        clearButtonTitle: 'Revenir à 0',
        clearCaption: '0 points'
    };
})(window.jQuery);
