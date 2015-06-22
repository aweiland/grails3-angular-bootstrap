<!doctype html>
<html>
    <head>
        <meta name="layout" content="bootstrap"/>
        <title>Angular Demos</title>
    </head>
    <body>
        <div class="row">
            <h1>Glyphicons</h1>
            <bootstrap:glyphicon icon="time" />
            <bootstrap:glyphicon icon="print" />
            <bootstrap:glyphicon icon="play" />
        </div>
        <div class="row" ng-controller="AccordianDemoCtrl">
            <h1>Accordian</h1>
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
