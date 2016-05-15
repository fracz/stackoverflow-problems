# Generated on Fri May 13 2016 13:25:00 GMT-0400 (EDT)

module.exports = (config) ->
  config.set
    basePath: './'
    frameworks: [
      'jasmine'
      'chai'
    ]
    files: [
      'app/*.coffee'
    ]
    exclude: []
    preprocessors: {
      'app/*.coffee' : ['coffee']
    }
    reporters: [ 'progress' ]
    port: 9876
    colors: true
    logLevel: config.LOG_INFO
    autoWatch: true
    browsers: [ 'Chrome' ]
    singleRun: false
    concurrency: Infinity