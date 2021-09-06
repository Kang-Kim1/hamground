<?php
  // 네이버 데이터랩 통합검색어 트렌드 Open API 예제
  $client_id = "iv96bOF00UinlIxDqsMY"; // 네이버 개발자센터에서 발급받은 CLIENT ID
  $client_secret = "Nv36500WrU";// 네이버 개발자센터에서 발급받은 CLIENT SECRET

  $con=mysqli_connect("localhost", "root", "1232", "myapp");

  // mysqli_connect()에 대한 마지막 호출에 대한 오류 코드 값을 반환한다.
  // echo mysqli_connect() 에서 0은 오류가 발생하지 않았음을 의미한다
  if (mysqli_connect_errno($con)) {
      echo "Failed to connect to MySQL: " . mysqli_connect_error();
  }

  for ($x = 1; $x <= 10; $x++) {
    $start = ($x * 100) + 1;
    echo "**** START : ";
    echo $start."<br/>";
    $encText = urlencode("햄스터");
    $url = "https://openapi.naver.com/v1/search/shop?display=100&start=". $start ."&query=".$encText;
    $is_post = false;
    echo "URL : ".$url."<br/>";
    $ch = curl_init(); //세션 초기화
  
    curl_setopt($ch, CURLOPT_URL, $url);
    curl_setopt($ch, CURLOPT_POST, $is_post);
    curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);
  
    $headers = array();
    $headers[] = "X-Naver-Client-Id: ".$client_id;
    $headers[] = "X-Naver-Client-Secret: ".$client_secret;
  
    curl_setopt($ch, CURLOPT_HTTPHEADER, $headers);
    curl_setopt($ch, CURLOPT_SSL_VERIFYPEER, 0);

    $response = curl_exec ($ch);
    $status_code = curl_getinfo($ch, CURLINFO_HTTP_CODE);
    //echo "status code: ".$status_code."";
  
    curl_close ($ch);
    if($status_code == 200) { //정상
      //echo $response;
    }

    $json = file_get_contents($response);
    $dataArr = json_decode($response, TRUE)['items'];

    // echo "<pre>";
    // echo $dataArr[0]['productId'];
    // echo "</br>";
    // echo count($dataArr[0]);
    // echo "</pre>";
    
    foreach($dataArr as $data) {
      $items_sql[] = "('{$data['productId']}', '{$data['title']}', '{$data['link']}','{$data['image']}','{$data['lprice']}','{$data['hprice']}','{$data['mallName']}','{$data['brand']}','{$data['maker']}','{$data['category1']}','{$data['category2']}','{$data['category3']}','{$data['category4']}')";

      $insertSQL = "INSERT INTO `hamster_items` (`id`, `name`, `link`, `image`, `l_price`, `h_price`, `mall`, `brand`, `maker`, `category1`, `category2`, `category3`, `category4`) VALUES ". implode(", ", $items_sql);
      
      echo "</br> SQL : ".$insertSQL;
      
      $result = mysqli_query($con, $insertSQL);
      unset($items_sql);
      if($result === false){
        echo mysqli_error($con);
      }
    }
  }
?>