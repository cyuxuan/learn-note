#include<iostream>

using namespace std;

class Test
{
private:
    /* data */
public:
    Test(/* args */);
    ~Test();
};

Test::Test()
{
    cout << "构造方法";
}

Test::~Test()
{
    cout << "析构方法";
}

int main()
{
    Test* test = new Test();
    test->~Test();
    return 1;
}