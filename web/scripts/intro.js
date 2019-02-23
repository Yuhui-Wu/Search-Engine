class StickyNavigation {

    constructor() {
        this.currentId = null;
        this.currentTab = null;
        this.tabContainerHeight = 70;
        var self = this;
        $('.tab').click(function() {
            self.onTabClick(event, $(this));
        });
        $(window).scroll(() => { this.onScroll(); });
        $(window).resize(() => { this.onResize(); });
    }

    onTabClick(event, element) {
        event.preventDefault();
        var scrollTop = $(element.attr('href')).offset().top - this.tabContainerHeight + 1;
        $('html, body').animate({ scrollTop: scrollTop }, 600);
    }

    onScroll() {
        this.checkTabContainerPosition();
        this.findCurrentTabSelector();
    }

    onResize() {
        if(this.currentId) {
            this.setSliderCss();
        }
    }

    checkTabContainerPosition() {
        var offset = $('.tabs').offset().top + $('.tabs').height() - this.tabContainerHeight;
        if($(window).scrollTop() > offset) {
            $('.tabs-container').addClass('tabs-container--top');
        }
        else {
            $('.tabs-container').removeClass('tabs-container--top');
        }
    }

    findCurrentTabSelector(element) {
        var newCurrentId;
        var newCurrentTab;
        var self = this;
        $('.tab').each(function() {
            var id = $(this).attr('href');
            var offsetTop = $(id).offset().top - self.tabContainerHeight;
            var offsetBottom = $(id).offset().top + $(id).height() - self.tabContainerHeight;
            if($(window).scrollTop() > offsetTop && $(window).scrollTop() < offsetBottom) {
                newCurrentId = id;
                newCurrentTab = $(this);
            }
        });
        if(this.currentId != newCurrentId || this.currentId === null) {
            this.currentId = newCurrentId;
            this.currentTab = newCurrentTab;
        }
    }

}

new StickyNavigation();


