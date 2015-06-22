<!doctype html>
<html>
    <head>
        <meta name="layout" content="bootstrap"/>
        <title>Angular Demos</title>
    </head>
    <body>
        <div class="row" ng-controller="AccordianDemoCtrl">
            <accordion>
                <accordion-group heading="Static Header, initially expanded" is-open="true">
                    This content was generated server side
                </accordion-group>
                <accordion-group heading="{{group.title}}" ng-repeat="group in groups">
                    {{group.content}}
                </accordion-group>
                <accordion-group heading="One last one">
                    This one is server side too
                </accordion-group>
            </accordion>

        </div>
    </body>
</html>
