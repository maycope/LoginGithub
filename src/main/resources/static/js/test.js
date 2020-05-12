$.getJSON("http://api.jirengu.com/getWeather.php?city=南京",
    function ({data}) {
        $("#des").text(data.results.index[0].title);

    });