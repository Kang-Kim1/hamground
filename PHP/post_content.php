<?php
    // DB 연동을 시작합니다.
    $con=mysqli_connect("localhost", "root", "1232", "myapp");

    // mysqli_connect()에 대한 마지막 호출에 대한 오류 코드 값을 반환한다.
    // echo mysqli_connect() 에서 0은 오류가 발생하지 않았음을 의미한다
    if (mysqli_connect_errno($con)) {
        echo "Failed to connect to MySQL: " . mysqli_connect_error();
    } 

    mysqli_set_charset($con, "utf8");


    if ($_SERVER['REQUEST_METHOD'] == "POST"){
        $json = file_get_contents('php//input');
        $data = json_decode($json,true);
        $title = $data['title'];
        $content = $data['content'];
        $category = $data ['category'];

        $response["code"] = 1;
        $response["server"] = $name . " : " . $guitar . " : " . $age;

        $res = mysqli_query($con, "INSERT INTO posting (p_title, p_content, p_writer, p_time, p_likes, p_count, p_category)
            VALUES (?, ?, "writer", NOW(), 0, 0, ?)");

        $stmt->bind_param("is", $title, $content, $category);
        $stmt->execute();

        echo json_encode($response);
    }

mysqli_close($con);
?>