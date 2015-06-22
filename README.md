# Grails 3 + Angular + Bootstrap


This is an experimental Grails project using Twitter Bootstrap for scaffolded views.  It has been updated to 
work with Grails 3.0.x and the lastest version of the twitter bootstrap plugin.


## Installation

You can either use this project as a template or to add to an existing Grails application:

1. Install the _Fields_ plugin, i.e. add `runtime ":fields:1.3"` to _BuildConfig.groovy_.
2. Copy the following files into your Grails application.
   * `src/templates/scaffolding/*` 
   * `grails-app/assets/**/*`
   * `grails-app/taglib/**/*`
   * `grails-app/views/index.gsp`
   * `grails-app/views/layouts/bootstrap.gsp`
   * `grails-app/views/_fields/default/_field.gsp`
   
   
Using this project as a template is the easiest way to get running quickly.
   
## Credits

The [Original version](https://github.com/robfletcher/twitter-bootstrap-scaffolding) needed some updates, so I made them.

Some common components borrowed from [KickstartWithBootstrap](https://github.com/joergrech/KickstartWithBootstrap).  

## Future

I'd like to break this out as a plugin for people to quick start things, sort of like [this plugin](http://grails.org/plugin/kickstart-with-bootstrap) but up to date.

In maybe a year or so, when Java 1.8 is more mainstream and Grails supports it, remove the Joda dependency and use the new 1.8 date libraries.