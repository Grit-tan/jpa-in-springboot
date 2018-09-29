package com.grit.learning.repository;

import com.grit.learning.domain.User;
import java.text.DateFormat;
import java.util.Date;
import javax.annotation.Resource;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRepositoryTests {

    @Resource
    private UserRepository userRepository;

    @Test
    public void test() {
        Date date = new Date();
        DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG);
        String formattedDate = dateFormat.format(date);

        userRepository.save(new User("aa", "aa@126.com", "aa", "aa123456", formattedDate));
        userRepository.save(new User("bb", "bb@126.com", "bb", "bb123456", formattedDate));
        userRepository.save(new User("cc", "cc@126.com", "cc", "cc123456", formattedDate));

        Assert.assertEquals(3, userRepository.findAll().size());
    }

    @Test
    public void test1() {
        Assert.assertEquals("bb123456",
                userRepository.findByUserNameOrEmail("bb", "cc@126.com").getNickName());
    }

    @Test
    public void test2() {
        Assert.assertEquals("bb123456",
                userRepository.findByUserNameOrEmail("bb", "cc@126.com").getNickName());
    }

    @Test
    public void test3() {
        userRepository.delete(userRepository.findByUserName("aa"));
    }

    @Test
    public void testPageQuery(){
        int page=1,size=10;
        Sort sort = new Sort(Sort.Direction.DESC, "id");
        Pageable pageable = PageRequest.of(page, size, sort);
        userRepository.findALL(pageable);
        userRepository.findByNickName("bb", pageable);
    }
}
