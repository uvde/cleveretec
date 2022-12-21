package ru.clevertec.vasili.urusov.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.clevertec.vasili.urusov.model.dto.OrderOfClientDTO;
import ru.clevertec.vasili.urusov.model.dto.Check;
import ru.clevertec.vasili.urusov.output.CheckOutput;
import ru.clevertec.vasili.urusov.output.OutputFactory;
import ru.clevertec.vasili.urusov.render.Render;
import ru.clevertec.vasili.urusov.render.impl.RenderCheck;
import ru.clevertec.vasili.urusov.render.impl.RenderObverseInformation;
import ru.clevertec.vasili.urusov.service.CheckService;
import ru.clevertec.vasili.urusov.service.OrderManagerService;

@Service
@RequiredArgsConstructor
public class CheckServiceImpl implements CheckService {

    private final OrderManagerService orderManagerService;


    public String createCheck(OrderOfClientDTO orderOfClientDTO) {
        Check check = orderManagerService.creatCheck(orderOfClientDTO);
        Render render = new RenderObverseInformation(new RenderCheck(check));

        CheckOutput checkOutput = OutputFactory.getInstance().getCheckRender("file");
        checkOutput.setCheck(render.render());
        checkOutput.output();

        CheckOutput consoleRender =  OutputFactory.getInstance().getCheckRender("console");
        consoleRender.setCheck(render.render());
        consoleRender.output();
        return render.render();
    }
}
