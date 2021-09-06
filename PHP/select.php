<?php
    // DB 연동을 시작합니다.
    $con=mysqli_connect("localhost", "root", "1232", "myapp");

    // mysqli_connect()에 대한 마지막 호출에 대한 오류 코드 값을 반환한다.
    // echo mysqli_connect() 에서 0은 오류가 발생하지 않았음을 의미한다
    if (mysqli_connect_errno($con)) {
        echo "Failed to connect to MySQL: " . mysqli_connect_error();
    }


    /* select data 
    // 1. 실행할 쿼리문을 작성합니다.
    $selectSQL = "select * from movie";
    $result = mysqli_query($con, $selectSQL);

    // 출력할 데이터를 저장할 배열변수 선언
    // 데이터는 json-array 형식으로 출력할 것임 (일반적으로 이렇게 함)
    $data = array();
    
    if ($result) {
        while ($row=mysqli_fetch_array($result)) {
            array_push($data,array('m_id'=>$row[0],'m_title'=>$row[1], 'm_year'=>$row[2], 'm_year'=>$row[2], 'm_img'=>$row[3], 'm_type'=>$row[4]));
        }
        header('Content-Type: application/json; charset=utf8');
        $json = json_encode($data, JSON_PRETTY_PRINT+JSON_UNESCAPED_UNICODE);

		echo $json; // => 출력되는 값이 이 코드로 하여금 android로 전송된다..

	} else {
        echo "SQL문 처리중 에러 발생 : ";
        echo mysqli_error($con);
    }

    // DB 연동을 종료 합니다
    mysqli_close($con);
    */
    $charArr = str_split("abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890");
    
    foreach($charArr as $char) {
       //echo $char;
    }

	
    $url = "https://www.omdbapi.com/?apikey=25b23d5b&type=&s=frozen&y=&page=1";
    $json = file_get_contents($url);
    $dataArr = json_decode($json, TRUE)['Search'];

echo "<pre>";
    print_r($dataArr[0]['Title']);
echo "</pre>";


    foreach($charArr as $char) {
       //echo $char;
    }

    $arrrr = ["5","3","3"];
    $insertSQL = "INSERT INTO movie VALUES ($arrrr[0], 'tt', 'tt', 'tt', 'tt')";
    echo $insertSQL;
    $result = mysqli_query($con, $insertSQL);
    
    if($result === false){
       echo mysqli_error($con);
    }

    $insertSQL = "INSERT INTO movie VALUES ('tt3', 'tt', 'tt', 'tt', 'tt')";
    $result = mysqli_query($con, $insertSQL);
?>